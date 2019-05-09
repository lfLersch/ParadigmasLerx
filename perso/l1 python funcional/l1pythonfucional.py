# 1 - Crie uma função sumSquares :: Int -> Int -> Int que calcule a soma dos quadrados de dois números x e y.
def sumSquares(x, y): 
	return x**2 + y**2

# 2 - Crie uma função hasEqHeads :: [Int] -> [Int] -> Bool que verifique se 2 listas possuem o mesmo primeiro elemento.
# Use a função head e o operador lógico == para verificar igualdade.
def hasEqHeads(x , y): 
	return l1[0] == l2[0]

# 3 - Escreva uma função que receba uma lista de nomes e adicione a string "Super " no início de cada nome.
# Use o operador ++ para concatenar strings (ou qualquer lista).
def putSuper(x):
	return list(map(lambda y: "Super " ++ y, x))

# 4 - Crie uma função que receba uma string e retorne o número de espaços nela contidos. 
# Dica: aplique 2 funções consecutivamente.
def Spaces(x):
	return len(list(filter(lambda y: y ==' ',x)))

# 5 - Escreva uma função que, dada uma lista de números, calcule 3*n^2 + 2/n + 1 para cada número n da lista.
# Dica: defina uma função anônima.
def func5(x):
	return list(map(lambda y: (3 * (y ** 2) + (2 / y) + 1), x))

# 6 - Escreva uma função que, dada uma lista de números, selecione somente os que forem negativos.
def Negative(x):
	return list(filter(lambda y: y < 0,x))

# 7 - Escreva uma função que receba uma lista de números e retorne somente os que estiverem entre 1 e 100,
# inclusive. Dica 1: defina uma função anônima. Dica 2: use o operador && para expressar um 'E' lógico.
def func7(x):
	return list(filter(lambda y: y >= 1 and y <= 100 ,x))

# 8 - Escreva uma função que, dada uma lista de idades de pessoas no ano atual,
# retorne uma lista somente com as idades de quem nasceu depois de 1980.
# Para testar a condição, sua função deverá subtrair a idade do ano atual.
def age1980(x):
	return list(filter(lambda y: y < (2019 - 1980), x))

# 9 - Escreva uma função que receba uma lista de números e retorne somente aqueles que forem pares.
def pair(x):
	return list(filter(lambda y: y % 2 == 0, x))

# 10 - Crie uma função charFound :: Char -> String -> Bool que verifique se o caracter (primeiro argumento)
# está contido na string (segundo argumento). Exemplos de uso da função:
def charFound(x, y):
	return bool(list(filter(lambda z: z == x, y)))

# 11 - Crie uma função que receba uma lista de nomes e retorne outra lista com somente aqueles nomes
# que terminarem com a letra 'a'. Dica: conheça o list monster, do autor Miran Lipovača :-)
def endA(x):
	return list(filter(lambda y: y[-1] == 'a', x))

print('asddsad')






