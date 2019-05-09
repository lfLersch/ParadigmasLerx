# Eliza bot explanation

- A ElisaBot é composta por vários predicados, os quais podem ser divididos em principais e secundarios. 

- Os predicados principais tem 2 parâmetros, a palavra chave e a prioridade para entrar no predicado

- Os predicados secundários(que ficam dentro do principal) são compostos por 3 parâmetros
	
	1. Um padrão numeral
	2. Um padrão de plavras(que será usado para chegar definitivamente nas respostas)
	3. Um índice que aponta aponta a resposta a ser escolhida(caso exista mais de uma ele vai incrementando a cada entrada do predicado)

- Quando voce digita algo, o que você digitou é transformado em uma string,  essa string é divida em uma lista de
 strings, esta navega pelos predicados do bot, se dentro da lista tiver a palavra chave de algum predicado, o bot 
vai entrar neste predicado(se o bot encontrar mais de uma palavra chave na lista, o predicado de maior prioridade 
será escolhido)

- Ao entrar definitivamente no predicado, o bot seleciona a resposta, escreve na tela e incrementa o indice das respostas do predicado ao qual entrou (3º parâmetro do predicado secundario)

- Foram acrescentados no bot 3 novos predicados

	1. Predicado game: 5 predicados secundários(rpg game, soccer game, fps game, moba game, vazio).
	2. Predicado Course: 1 predicado vazio e 3 respostas
	3. Predicado Anime: 5 predicados secundarios(rpg anime, soccer anime, ninja anime, special skills anime, vazio)
	4. Predicado Chicken: 2 predicados secundarios(Why the chicken cross the street, who comes first the egg or the chicken)

