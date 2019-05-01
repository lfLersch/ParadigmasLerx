segunda(pedro,santaMaria).
segunda(caren,portoAlegre).
segunda(henrique,apartamento).
segunda(bia, apartamento).
segunda(adriano, apartamento).
segunda(alice, apartamento).
segunda(bernardo, portoAlegre).
segunda(maria, apartamento).

terca(pedro,santaMaria).
terca(caren,portoAlegre).
terca(henrique,portoAlegre).
terca(bia, portoAlegre).
terca(adriano, apartamento).
terca(alice, portoAlegre).
terca(bernardo, portoAlegre).
terca(maria, santaMaria).

quarta(pedro,portoAlegre).
quarta(caren,portoAlegre).
quarta(henrique,apartamento).
quarta(bia, portoAlegre).
quarta(adriano, santaMaria).
quarta(alice, portoAlegre).
quarta(bernardo, santaMaria).
quarta(maria, santaMaria).

quinta(pedro,santaMaria).
quinta(caren,santaMaria).
quinta(henrique,apartamento).
quinta(bia, santaMaria).
quinta(adriano, apartamento).
quinta(alice, apartamento).
quinta(bernardo, portoAlegre).
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



acesso(X) :- ((quarta(X, apartamento); quarta(X, santaMaria);quinta(X, portoAlegre)),
(quinta(X, apartamento); sexta(X, apartamento)),
(terca(X, portoAlegre)));

((quarta(X, apartamento);quinta(X, portoAlegre)),
(quinta(X, apartamento); sexta(X, apartamento)),
(quarta(X, santaMaria); terca(X, portoAlegre)));

((quinta(X, apartamento); quarta(X, apartamento); quarta(X, santaMaria);quinta(X, portoAlegre)),
(sexta(X, apartamento)),
(terca(X, portoAlegre)));

((quinta(X, apartamento); quarta(X, apartamento);quinta(X, portoAlegre)),
(sexta(X, apartamento)),
(quarta(X, santaMaria); terca(X, portoAlegre))).


assassino(X,Y) :- motivo(X,Y), acesso(X).
