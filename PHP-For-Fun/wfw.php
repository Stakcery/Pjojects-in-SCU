<?php
function curl_get($url,$cookie='', $returnCookie=0){
    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, $url);
    curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, FALSE);
    curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, FALSE);
    curl_setopt($curl, CURLOPT_USERAGENT, 'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)');
    curl_setopt($curl, CURLOPT_FOLLOWLOCATION, 1);
    curl_setopt($curl, CURLOPT_AUTOREFERER, 1);
//    curl_setopt($curl, CURLOPT_REFERER, "http://XXX");
    if($cookie) {
        curl_setopt($curl, CURLOPT_COOKIE, $cookie);
    }
    curl_setopt($curl, CURLOPT_HEADER, $returnCookie);
    curl_setopt($curl, CURLOPT_TIMEOUT, 10);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    $data = curl_exec($curl);
    if (curl_errno($curl)) {
        return curl_error($curl);
    }
    curl_close($curl);
    if($returnCookie){
        list($header, $body) = explode("\r\n\r\n", $data, 2);
        preg_match_all("/Set\-Cookie:([^;]*);/", $header, $matches);
        $info['cookie']  = substr($matches[1][0], 1);
        $info['content'] = $body;
        return $info;
    }else{
        return $data;
    }
};
function curl_post($url,$post='',$cookie='', $returnCookie=0){
    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, $url);
    curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, FALSE);
    curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, FALSE);
    curl_setopt($curl, CURLOPT_USERAGENT, 'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)');
    curl_setopt($curl, CURLOPT_FOLLOWLOCATION, 1);
    curl_setopt($curl, CURLOPT_AUTOREFERER, 1);
//    curl_setopt($curl, CURLOPT_REFERER, "http://XXX");
    if($post) {
        curl_setopt($curl, CURLOPT_POST, 1);
        curl_setopt($curl, CURLOPT_POSTFIELDS, http_build_query($post));
    }
    if($cookie) {
        curl_setopt($curl, CURLOPT_COOKIE, $cookie);
    }
    curl_setopt($curl, CURLOPT_HEADER, $returnCookie);
    curl_setopt($curl, CURLOPT_TIMEOUT, 10);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    $data = curl_exec($curl);
    if (curl_errno($curl)) {
        return curl_error($curl);
    }
    curl_close($curl);
    if($returnCookie){
        list($header, $body) = explode("\r\n\r\n", $data, 2);
        preg_match_all("/Set\-Cookie:([^;]*);/", $header, $matches);
        $info['cookie']  = substr($matches[1][0], 1);
        $info['content'] = $body;
        return $info;
    }else{
        return $data;
    }
};
function unescape($str) {
    $str=str_replace("\\","%",$str);
    $str = rawurldecode($str);
    preg_match_all("/(?:%u.{4})|.{4};|&#\d+;|.+/U",$str,$r);
    $ar = $r[0];
    foreach($ar as $k=>$v) {
        if(substr($v,0,2) == "%u")
            $ar[$k] = iconv("UCS-2","utf-8",pack("H4",substr($v,-4)));
        elseif(substr($v,0,3) == "")
            $ar[$k] = iconv("UCS-2","utf-8",pack("H4",substr($v,3,-1)));
        elseif(substr($v,0,2) == "&#") {
            $ar[$k] = iconv("UCS-2","utf-8",pack("n",substr($v,2,-1)));
        }
    }
    $str2 = join("",$ar);
    $str = str_replace("%","",$str2);
    return $str;
}
$username = $_POST['username'];
$password = $_POST['password'];
if($username && $password){
    $dd = array('username'=>$username ,'password'=>$password,'redirect'=>'https://wfw.scu.edu.cn/ncov/wap/default/index');
    $rt = curl_post('https://wfw.scu.edu.cn/a_scu/api/sso/check',$dd, 1,1);
    foreach($rt as $value){
        if('eai-sess'==substr($value,0,8)){
            $cookie_new = $value;
        }else{
            $rt = json_decode($value, true);
            if('操作成功' == $rt['m']){
                $rt = curl_get('https://wfw.scu.edu.cn/ncov/wap/default/index',$cookie=$cookie_new);
                $rt = preg_match('/.*?oldInfo: (.*),.*?/',$rt, $mat);
                $rt = json_decode($mat[1], true);
                $dt = curl_post('https://wfw.scu.edu.cn/ncov/wap/default/save',$post= $rt,$cookie=$cookie_new);
                echo $dt;
            }
        }

    }
}else{
    echo '{"e":1,"m":"学号密码不能为空","d":{}}';
}


?>
