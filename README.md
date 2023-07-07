WallConstruct
--- 

Zadanie polegało na stworzeniu 3 metod.  
Z uwzględnieniem w analizie i implementacji interfejsu CompositeBlock

##

Metody zwracającej dowolny element o podanym kolorze.

Metody zwracającej wszystkie elementy z danego materiału.

Metody zwracającej liczbę wszystkich elementów tworzących strukturę.

##

Na początku napisałem 3 proste metody, które zwracały w oparciu o element block.
Podczas pisania metody count myślałem o implementacji interfejsu CompositeBlock 
gdzie metoda powinna policzyć nie tylko block, ale również CompositeBlock i jego elementy.
Założyłem, że CompositeBlock może zawierać listę elementów block oraz CompositeBlock.

Po napisaniu metody count oraz jej rekurencji zwróciłem uwagę, że 2 pierwsze metody 
równiez mogą zawierać CompositeBlock i w nich również metoda powinna uwzględnić
poszukiwania w CompositeBlock.

Do rozwiązania problemu posłużyłem się stream. Metody oraz ich rekurencje są w stream.
W metodach rekurencji w stream zastosowałem .filtr oraz operatory AND i OR 
do wywołania rekurencji podczas streamu.
