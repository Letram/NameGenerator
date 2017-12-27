import sqlite3

connection = sqlite3.connect("names.db")
cursor = connection.cursor()

cursor.execute('DROP TABLE IF EXISTS names ')
cursor.execute('CREATE TABLE names (name TEXT)')
cursor.execute('DROP TABLE IF EXISTS lastnames ')
cursor.execute('CREATE TABLE lastnames (lastname TEXT)')
try:
    nameFile = open("resources/firstname.txt", 'r')
    print("Opening names file.")
    names = nameFile.read().split("\n")

    print("Populating 'names' table...")
    for name in names:
        cursor.execute("INSERT INTO names (name) VALUES (?)", [name])
    print("Done")
except Exception:
    print("Can't open file (name).")

try:
    lastnameFile = open("resources/lastname.txt", 'r')
    print("Opening lastnames file.")
    lastnames = lastnameFile.read().split("\n")

    print("Populating 'lastnames' table...")
    for lastname in lastnames:
        cursor.execute("INSERT INTO lastnames (lastname) VALUES (?)", [lastname])
    print("Done")
except Exception:
    print("Can't open file (lastname).")

connection.commit()
cursor.close()
connection.close()
