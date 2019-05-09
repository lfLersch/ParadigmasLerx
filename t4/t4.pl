segunda(pedro,santaMaria).
segunda(caren,portoAlegre).
segunda(henrique,apartamento).
segunda(bia, apartamento).
segunda(adriano, apartamento).
segunda(alice, apartamento).
segunda(bernardo, santaMaria).
segunda(maria, apartamento).

terca(pedro,santaMaria).
terca(caren,portoAlegre).
terca(henrique,portoAlegre).
terca(bia, portoAlegre).
terca(adriano, apartamento).
terca(alice, portoAlegre).
terca(bernardo, santaMaria).
terca(maria, santaMaria).

quarta(pedro,portoAlegre).
quarta(caren,portoAlegre).
quarta(henrique,apartamento).
quarta(bia, portoAlegre).
quarta(adriano, santaMaria).
quarta(alice, portoAlegre).
quarta(bernardo, portoAlegre).
quarta(maria, santaMaria).

quinta(pedro,santaMaria).
quinta(caren,santaMaria).
quinta(henrique,apartamento).
quinta(bia, santaMaria).
quinta(adriano, apartamento).
quinta(alice, apartamento).
quinta(bernardo, santaMaria).
quinta(maria, santaMaria).

sexta(pedro,apartamento).
sexta(caren,apartamento).
sexta(henrique,apartamento).
sexta(bia, apartamento).
sexta(adriano, apartamento).
sexta(alice, apartamento).
sexta(bernardo, apartamento).
sexta(maria, apartamento).


insano(adriano).
insano(maria).

pobre(bia).
pobre(pedro).
pobre(maria).
pobre(bernardo).

relacionamento(anita, bernardo).
relacionamento(caren, bernardo).
relacionamento(anita, pedro).
relacionamento(alice, pedro).
relacionamento(alice, henrique).
relacionamento(maria, henrique).
relacionamento(maria, adriano).
relacionamento(caren, adriano).

vitima(anita).
motivo(X, ciumes) :- relacionamento(X,Y), relacionamento(anita,Y).
motivo(X, loucura) :- insano(X).
motivo(X, dinheiro) :- pobre(X).



acesso(X) :- 
(quinta(X, portoAlegre); quarta(X, santaMaria); quarta(X, apartamento); quinta(X, apartamento)),
(segunda(X, santaMaria); terca(X, portoAlegre)),
(quinta(X, apartamento); sexta(X, apartamento)).

assassino(X,Y) :- motivo(X,Y), acesso(X).
