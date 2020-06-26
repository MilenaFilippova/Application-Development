@author: Милена Владимировна
"""

import csv
import json

csvfile = open('D:\\ИГУ\\3курс\\Разработка приложений\\2cеместр\\googleplaystore.csv', encoding='utf-8')
jsonfile = open('D:\\ИГУ\\3курс\\Разработка приложений\\2cеместр\\google.json', 'w')

fieldnames = ("App", "Category", "Rating", "Reviews", "Size", "Installs", "Type", "Price", "Content Rating", "Genres", "Last Updated", "Current Ver", "Android Ver")
reader = csv.DictReader(csvfile, fieldnames)

record = {}

i = 0

for row in reader:
    for name in fieldnames:
        record[name] = row[name]
    record['Installs'] = record['Installs'][:-1]
    record['Installs'] = str(record['Installs']).replace(',', '')
    record['Android Ver'] = str(record['Android Ver']).split()[0]
    record['Price'] = True if record['Type'] == "Free" else False
    record['Genres'] = "[" + ", ".join(record['Genres'].split(";")) + "]"
    line = json.dumps(record)
    jsonfile.write(line + ",\n")
print("файл готов")
csvfile.close()
jsonfile.close()
