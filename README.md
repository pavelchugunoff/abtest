# abtest

Тестовое задание от альфа банка. Выполнены все условия.

## Запуск
#####Инструкция по запуску:
###### docker build -t alfabanktest .
###### docker run -p 8000:8000 -t abtest

##### После запуска приложения переходим на http://localhost:8000 и при вводе кода валюты отображается gif из трех категорий:
* rich
* broke
* zero

##### rich и broke выведутся если курс валюты упал ии поднялся, но если изменений за день не произойдет то отобразится категория zero.
