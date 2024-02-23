# Использование
Опции:
- (обязательный) `--matrix`, `-m` матрица для преобразования в формате "[[...,0,...,1,...],[...],...]".
- (не обязательный) `--isParityCheck`, `-p` задана проверочная матрица.
- `--help`, `-h` информация об использовании.

Пример использования:
`{executable} -m [[0,1,1,1,0,0],[1,0,1,0,1,0],[1,1,0,0,0,1]] -p`

Тогда проверочная матрица `[[0,1,1,1,0,0],[1,0,1,0,1,0],[1,1,0,0,0,1]]` будет преобразована в порождающую через теорему о каноническом виде.