import Data.Char

-- 1)

isVowel :: Char -> Bool

isVowel x = if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u' || x == 'A' || x == 'E' || x == 'I' || x == 'O' || x == 'U') then True else False




-- 2)


addComma  :: [String] -> [String]

addComma x = map( ++ ",") x



-- 3)



-- SEM FUNÇÕES ANONIMAS



addstr :: String -> String
addstr str = "<LI>" ++ str ++ "</LI>" 


htmlListItemsSem :: [String] -> [String]
htmlListItemsSem x = map(addstr)x



-- COM FUNÇÕES ANONIMAS

htmlListItemsCom :: [String] -> [String]


htmlListItemsCom x = map(\str -> "<LI>" ++ str ++ "</LI>" )x



-- 4)



-- SEM FUNÇÕES ANONIMAS



isNotVowel :: Char -> Bool

isNotVowel c = c `notElem` "aeiouAEIOU"


noVowelsSEM :: String -> String

noVowelsSEM x = filter(isNotVowel)x



-- COM FUNÇÕES ANONIMAS



noVowelsCOM :: String -> String

noVowelsCOM x = filter(\str -> str `notElem` "aeiouAEIOU")x



-- 5)

-- SEM FUNÇÕES ANONIMAS

isNotSpace :: Char -> Char


isNotSpace c = if (c == ' ') then ' ' else '-'


onlySpaceSem :: String -> String
onlySpaceSem x = map(isNotSpace)x



-- COM FUNÇÕES ANONIMAS

onlySpaceCOM :: String -> String
onlySpaceCOM x = map(\c -> if (c == ' ') then ' ' else '-')x


-- 6)

firstName :: String -> String
firstName x = takeWhile(/=' ')x

-- 7)

isInt :: String -> Bool
isInt x = if(length(filter(`notElem` "0123456789")x)) ==0 then True else False

-- 8)

lastName :: String -> String
lastName x = reverse(takeWhile(/=' ')(reverse x))

-- 9)
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

-- 11)

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

-- 12)

montaString :: String -> String
montaString s = if(length(take 10 s) < 10) then montaString (s++".")	
	else
	 	(take 10 s)


func :: [String] -> [String]
func x = map(montaString)x














