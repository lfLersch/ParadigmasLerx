#Eliza bot explanation

- Os predicados principais tem 2 parametros, a palavra chave e a prioridade para entrar no predicado

- Dentro do predicado principal existe outro predicado(podem ser colocados mais) com 3 parametros,o primeiro
 representa o padrão numeral, o segundo o padrao de palavras para entrar definitivamente no predicado e entrar na
  fala da elisa, e o ultimo representa a ultima resposta usada(com auto-increment)

- Quando voce digita algo, essa string é divida em uma lista de strings, que navega pelos predicados do bot, e se 
dentro da string tiver a palavra chave de algum predicado, o bot vai entrar no predicado de maior prioridade com a 
palavra chave, logo após entrando nas respostas

