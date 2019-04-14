import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)


--mmod :: Int-> Int
--mmod x = mod 
-------------------------------------------------------------------------------
-- Paletas
-------------------------------------------------------------------------------

rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n $ cycle [(255,0,0),(0,255,0),(0,0,255)]

greenPalette :: Int -> Int -> [(Int,Int,Int)]
greenPalette n x = [(0,x+i*3,0) | i <- [0..n] ]

listaRGB :: [Int]
listaRGB = [0,0,0,0,0,125,125,250,250,250,125,125]

pegaRGB :: Int -> [Int] -> Int -> Int
pegaRGB x y z = if z == x then (head y) else (pegaRGB x (tail y) (z+1))

palette2 ::  Int -> Int -> Int -> [(Int,Int,Int)]
palette2 r g b = [(pegaRGB (r+i) listaRGB 0 ,pegaRGB (g+i) listaRGB 0 ,pegaRGB (b+i) listaRGB 0) | i <- [0..3]]

palette3 ::  Int -> Int -> Int -> Int -> [(Int,Int,Int)]
palette3 x y z n = [(255*x,255*y,255*z) | i <- [0..(n-1)] ]

palette4 :: Int -> Int -> Int -> Int -> [(Int,Int,Int)]
palette4 x y z n = [((50+i*10)*x,(50+i*10)*y,(50+i*10)*z) | i <- [0..n]]

paletteN :: Int ->[(Int,Int,Int)]
paletteN 0 = [(255,165,0)]
paletteN 1 = [(0,0,125)]
paletteN 2 = [(0,0,125)]
paletteN 3 = [(0,250,250)]


-------------------------------------------------------------------------------
-- Geração de retângulos em suas posições
-------------------------------------------------------------------------------

genRectsInLine :: Int -> Int -> [[Rect]]
genRectsInLine n x  = [[((m*(w+gap),fromIntegral(y)*75+25),w,h) | m <- [0..fromIntegral (n-1)]] | y <- [0..x]]
  where (w,h) = (50,50)
        gap = 10

radianos :: Float -> Float
radianos 0 = 0
radianos x = pi/x

genCircleInCircle :: [[Circle]]
genCircleInCircle = [[((200+100*(sin(radianos m)*x),200+100*(cos(radianos m)*x)),r) | m <- [(-2),(-3),(-6),0,6,3]] | x <- [1,(-1)]]
  where r = 20

genCircleInLine :: Int -> Int -> Float -> Float -> [[Circle]]
genCircleInLine n x a b  = [[(((150+m*(gap)-(a*b)),fromIntegral(y)*150+50+a*2),r) | m <- [0..fromIntegral (n-1)]] | y <- [0..(x-1)]]
  where gap = 200
        r = 40

genCircleInWave :: Float -> [[Circle]]
genCircleInWave n  = [[((100+60*m,50*(sin(m))+100*x),r) | m <- [0,0.448572..2*pi]] | x <- [1..n]]
  where gap = 50
        r = 20

genSubCircle :: Float -> [Circle]
genSubCircle n  = [((r*i,r*j),r) | i <- [1..n], j <- [1..n]]
  where gap = 50
        r = 80
 


-------------------------------------------------------------------------------
-- Strings SVG
-------------------------------------------------------------------------------

-- Gera string representando retângulo SVG 
-- dadas coordenadas e dimensoes do retângulo e uma string com atributos de estilo
svgRect :: Rect -> String -> String 
svgRect ((x,y),w,h) style = 
  printf "<rect x='%.3f' y='%.3f' width='%.2f' height='%.2f' style='%s' />\n" x y w h style

svgCircle :: Circle -> String -> String 
svgCircle ((x,y),r) style = 
  printf "<circle cx='%.3f' cy='%.3f' r='%.2f' style='%s' />\n" x y r style



-- String inicial do SVG
svgBegin :: Float -> Float -> String
svgBegin w h = printf "<svg width='%.2f' height='%.2f' xmlns='http://www.w3.org/2000/svg'>\n" w h 

-- String final do SVG
svgEnd :: String
svgEnd = "</svg>"

-- Gera string com atributos de estilo para uma dada cor
-- Atributo mix-blend-mode permite misturar cores
svgStyle :: (Int,Int,Int) -> String
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d); mix-blend-mode: screen;" r g b

-- Gera strings SVG para uma dada lista de figuras e seus atributos de estilo
-- Recebe uma funcao geradora de strings SVG, uma lista de círculos/retângulos e strings de estilo
svgElements :: (a -> String -> String) -> [a] -> [String] -> String
svgElements func elements styles = concat $ zipWith func elements styles

oneList :: [[a]] -> [a]
oneList [] = []
oneList x = head x ++ oneList (tail x)


-------------------------------------------------------------------------------
-- Função principal que gera arquivo com imagem SVG
-------------------------------------------------------------------------------

genCase1 :: IO ()
genCase1 = do
  writeFile "caseX.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgRect rects (map svgStyle palette)
        rects = oneList (genRectsInLine ncolumns nlines)
        palette = greenPalette nrects inicialcolor
        nrects = ncolumns * nlines -1
        ncolumns = 10
        nlines = 5
        inicialcolor = 80


genCase2 :: IO ()
genCase2 = do
  writeFile "caseX.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfgs ++ svgEnd
        svgfgs = svgElements svgCircle circle (map svgStyle palette)
        circle = oneList (genCircleInCircle)
        palette = palette2 8 4 0 ++ palette2 0 8 4 ++ palette2 4 0 8

(w,h) = (1500,500)

genCase3 :: IO ()
genCase3 = do
  writeFile "caseX.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfgs ++ svgEnd
        svgfgs = svgElements svgCircle circle (map svgStyle palette)
        circle = oneList (genCircleInLine 3 2 0 1) ++ oneList(genCircleInLine 3 2 20 1) ++ oneList(genCircleInLine 3 2 20 (-1))
        palette = (palette3 0 0 1 6) ++ (palette3 0 1 0 6) ++ (palette3 1 0 0 6)

genCase4 :: IO()
genCase4 = do
  writeFile "caseX.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfgs ++ svgEnd
        svgfgs = svgElements svgCircle circle (map svgStyle palette)
        circle = oneList(genCircleInWave 3)
        palette = (palette4 1 0 0 ncircle) ++ (palette4 0 1 0 ncircle) ++ (palette4 0 0 1 ncircle)
        ncircle = 14

genCase5 :: IO()
genCase5 = do
  writeFile "caseX.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfgs ++ svgEnd
        svgfgs = svgElements svgCircle circle (map svgStyle palette)
        circle = genSubCircle nside
        palette = oneList([paletteN (mod (m*n+x) 2) | x <- [1..n], m <- [1..n]])
        nside = 15
        n = 15

        

