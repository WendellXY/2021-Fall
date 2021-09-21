from sqlalchemy import create_engine
import sqlite3
import pandas as pd

print('This file is only used for init database, since I already provided the SQL database in the package, so it is unnecessary to execute again.')

db = pd.read_excel(
    'db_lec2.xls',
    sheet_name='gmet99',
    header=0
)
print(db.head())

con = sqlite3.connect('lec2.db')
wb = pd.read_excel('db_lec2.xls', sheet_name = 'gmet99')
wb.to_sql(name='gmet99',con=con,if_exists='replace',index=True)
con.commit()
con.close()

con = sqlite3.connect('lec2.db')

cursor = con.execute("SELECT Tmax, Tmin from gmet99")

for row in cursor:
    print(row)

def executeAndPrint(cmd, con):

    try:
        ret = con.execute(cmd)

        for row in ret:
            print(row)
    except:
        print("Execute Failed {0}".format(cmd))
        pass

# Remove unnecessary tables

uselessTableNames = ["Date", "Dis", "Sed.con", "Sed.load", "Tmin", "Tmean", "Tmax", "Year", "Month", "Sl.no.", "RH", "Dis."]

for prefixName in uselessTableNames:
    prefixCMD = "DROP TABLE"
    executeAndPrint('{0} "{1}"'.format(prefixCMD, prefixName), con)
    for i in range(1, 8):
        executeAndPrint('{0} "{1}"'.format(prefixCMD, prefixName + "." + str(i)), con)
