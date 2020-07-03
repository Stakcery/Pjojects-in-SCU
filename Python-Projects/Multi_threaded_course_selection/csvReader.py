import csv
import re
import os
import pandas as pd
import xlrd


def readInfo():
    path = os.getcwd() + "/info/course.txt"
    x = ""
    with open(path, "r") as f:
        x = f.readlines()
    return  x


def getUsername():
    path = os.getcwd() + "/info/userInfo.xlsx"
    data = xlrd.open_workbook(path)
    table = data.sheets()[0]
    nor = table.nrows
    nol = table.ncols
    dict = {}
    for i in range(1, nor):
        for j in range(nol):
            title = table.cell_value(0, j)
            value = table.cell_value(i, j)
            dict[title] = value
        yield dict




