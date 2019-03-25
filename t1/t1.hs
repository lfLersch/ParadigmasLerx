import Data.Char

-- 1) Crie uma função isVowel :: Char -> Bool que verifique se um caracter é uma vogal ou não.

isVowel :: Char -> Bool

isVowel x = if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u' || x == 'A' || x == 'E' || x == 'I' || x == 'O' || x == 'U') then True else False




-- 2) Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista


addComma  :: [String] -> [String]

addComma x = map( ++ ",") x



-- 3)Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista 
--	contendo as strings formatadas como itens de lista em HTML.
--	Resolva este exercício COM e SEM funções anônimas (lambda). Exemplo de uso da função:



-- SEM FUNÇÕES ANONIMAS



addstr :: String -> String
addstr str = "<LI>" ++ str ++ "</LI>" 


htmlListItemsSem :: [String] -> [String]
htmlListItemsSem x = map(addstr)x



-- COM FUNÇÕES ANONIMAS

htmlListItemsCom :: [String] -> [String]


htmlListItemsCom x = map(\str -> "<LI>" ++ str ++ "</LI>" )x



-- 4) Defina uma função que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo.
--	 Resolva este exercício COM e SEM funções anônimas (lambda).



-- SEM FUNÇÕES ANONIMAS



isNotVowel :: Char -> Bool

isNotVowel c = c `notElem` "aeiouAEIOU"


noVowelsSEM :: String -> String

noVowelsSEM x = filter(isNotVowel)x



-- COM FUNÇÕES ANONIMAS



noVowelsCOM :: String -> String

noVowelsCOM x = filter(\str -> str `notElem` "aeiouAEIOU")x



-- 5) Defina uma função que receba uma string, possivelmente contendo espaços, e que retorne outra string 
--	substituindo os demais caracteres por '-', mas mantendo os espaços. Resolva este exercício COM e SEM funções 
--	anônimas (lambda). Exemplos:

-- SEM FUNÇÕES ANONIMAS

isNotSpace :: Char -> Char


isNotSpace c = if (c == ' ') then ' ' else '-'


onlySpaceSem :: String -> String
onlySpaceSem x = map(isNotSpace)x



-- COM FUNÇÕES ANONIMAS

onlySpaceCOM :: String -> String
onlySpaceCOM x = map(\c -> if (c == ' ') then ' ' else '-')x


-- 6) Escreva uma função firstName :: String -> String que, dado o nome completo de uma pessoa, 
--	obtenha seu primeiro nome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços
--	no início ou fim do nome. Dica: estude funções pré-definidas em Haskell (List operations -> Sublists) em 
--	http://hackage.haskell.org/package/base-4.10.1.0/docs/Prelude.html#g:18. Exemplos de uso da função:

firstName :: String -> String
firstName x = takeWhile(/=' ')x

-- 7) Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.
--	Exemplos:

isInt :: String -> Bool
isInt x = if(length(filter(`notElem` "0123456789")x)) ==0 then True else False

-- 8) Escreva uma função lastName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu último
--	sobrenome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início
--	ou fim do nome. Exemplos de uso da função:

lastName :: String -> String
lastName x = reverse(takeWhile(/=' ')(reverse x))

-- 9) Escreva uma função userName :: String -> String que, dado o nome completo de uma pessoa, 
--	crie um nome de usuário (login) da pessoa, formado por: primeira letra do nome seguida do sobrenome,
--	tudo em minúsculas. Dica: estude as funções pré-definidas no módulo Data.Char, para manipulação de maiúsculas
--	e minúsculas. Você precisará carregar este módulo usando import Data.Char no interpretador ou no início
--	do arquivo do programa.

userName :: String -> String
userName x = map(toLower) ((take 1 x) ++ (lastName x))

-- 10)	Escreva uma função encodeName :: String -> String que substitua vogais em uma string,
-- 		conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.

swapLetter :: Char -> Char
swapLetter c
	    | c =='a' 		=  '4'
	    | c =='e' 		=  '3'
		| c =='i'	 	=  '2'
	  	| c =='o' 		=  '1'
	 	| c =='u' 		=  '0'
	  	| c =='A' 		=  '4'
	  	| c =='E' 		=  '3'
	  	| c =='I' 		=  '2'
	  	| c =='O' 		=  '1'
	  	| c =='U' 		=  '0'
	  	| c == c 		=  c
	    
encodeName :: String -> String
encodeName x = map(swapLetter)x

-- 11) Escreva uma função betterEncodeName :: String -> String que substitua vogais em uma string,
--	conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00. Exemplos de uso da função:

swapLetter2 :: Char -> String
swapLetter2 c
	    | c =='a' 		=  "4"
	    | c =='e' 		=  "3"
		| c =='i'	 	=  "2"
	  	| c =='o' 		=  "1"
	 	| c =='u' 		=  "00"
	  	| c =='A' 		=  "4"
	  	| c =='E' 		=  "3"
	  	| c =='I' 		=  "2"
	  	| c =='O' 		=  "1"
	  	| c =='U' 		=  "00"
	  	| c == c 		=  [c]
	    
betterEncodeName :: String -> String
betterEncodeName x = concatMap(swapLetter2)x

-- 12) Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema:
--	strings de entrada com mais de 10 caracteres são truncadas, strings com até 10 caracteres são completadas
--	com '.' até ficarem com 10 caracteres. Exemplo:

montaString :: String -> String
montaString s = if(length(take 10 s) < 10) then montaString (s++".")	
	else
	 	(take 10 s)


func :: [String] -> [String]
func x = map(montaString)x

