# Описание

Преобразует проверочные (порождающие) матрицы в двоичном виде с использованием теоремы о каноническом виде к
порождающим (проверочным).

# Использование

Опции:

- `--matrix`, `-m` матрица для преобразования в формате "[[...,0,...,1,...],[...],...]".
- `--isParityCheck`, `-p` задана проверочная матрица.
- `--help`, `-h` информация об использовании.
- `--outputFile`, `-o` путь до файла для записи результата.

Примеры использования:

1. `{executable} -m [[0,1,1,1,0,0],[1,0,1,0,1,0],[1,1,0,0,0,1]] -p`

2. `{executable} -m /users/user/git/canonical-converter/input.txt`
