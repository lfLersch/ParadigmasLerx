acesso2(X) :-
  Casas = [_, _, _, _], 	
  member(casa(pedro, portoAlegre, santaMaria, apartamento), Casas), 
  member(casa(caren, portoAlegre, santaMaria, apartamento), Casas),	
  member(casa(henrique, apartamento, apartamento, apartamento), Casas),	
  member(casa(bia, portoAlegre, santaMaria, apartamento), Casas),
  member(casa(adriano, santaMaria, apartamento, apartamento), Casas),
  member(casa(alice, portoAlegre, apartamento, apartamento), Casas),
  member(casa(bernardo, portoAlegre, santaMaria, apartamento), Casas),
  member(casa(maria, santaMaria, santaMaria, apartamento), Casas),
  member(casa(_, apartamento,_,apartamento)Casas).

apartamentoQuarta(henrique).
apartamentoQuinta(henrique).
apartamentoQuinta(adriano).
apartamentoQuinta(alice).
apartamentoSexta(pedro).
apartamentoSexta(caren).
apartamentoSexta(henrique).
apartamentoSexta(bia).
apartamentoSexta(adriano).
apartamentoSexta(alice).
apartamentoSexta(bernardo).
apartamentoSexta(maria).
santaMariaQuarta(adriano).
santaMariaQuarta(maria).
portoAlegreQuinta().


assassino(X) :- motivo(X), acesso(X).

acesso(X) :- (apartamentoQuinta(X); apartamentoQuarta(X); santaMariaQuarta(X)),
(apartamentoQuinta(X); apartamentoSexta(X)).

motivo(X) :- loucura(X); dinheiro(X); ciumes(X).

loucura(adriano).
loucura(maria).
dinheiro(bia).
ciumes(caren).