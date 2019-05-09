# Eliza bot explanation

- A ElisaBot é composta por vários predicados, os quais podem ser divididos em principais e secundarios. 

- Os predicados principais tem 2 parâmetros, a palavra chave e a prioridade para entrar no predicado

- Os predicados secundários(que ficam dentro do principal) são compostos por 3 parâmetros
	
	1. Um padrão numeral
	2. Um padrão de plavras(que será usado para chegar definitivamente nas respostas)
	3. Um índice que aponta aponta a resposta a ser escolhida(caso exista mais de uma ele vai incrementando a cada entrada do predicado)

- Quando voce digita algo, o que você digitou é transformado em uma string,  essa string é divida em uma lista de strings, esta navega pelos predicados do bot, e se 
dentro da lista tiver a palavra chave de algum predicado, o bot vai entrar neste predicado(se a lista puder entrar em mais de 1 predicado ela escolhera o de maior prioridade)

- Ao entrar definitivamente no predicado, o bot seleciona a resposta, escreve na tela e incrementa o indice das respostas do predicado ao qual entrou (3º parâmetro do predicado secundario)

