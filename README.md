# most-popular-product
Spark-приложение для определения самого популярного продукта у каждого клиента.
Если таких несколько, то выводятся все варианты, находящиеся на первом месте по полурярности.

Приложение принимает на вход три файла в формате `csv` и формирует выходной файл с колонками `customerName` и `productName` также в формате `csv`.
Образец выходного файла расположен [здесь](https://github.com/mikhaildruzhinin/most-popular-product/tree/main/data/result_example).
## Установка
Для работы с приложением требуется [Docker](https://www.docker.com/) и [sbt](https://www.scala-sbt.org/index.html).

Необходимо клонировать репозиторий проекта:
```
git clone git@github.com:mikhaildruzhinin/most-popular-product.git
```
Следующие действия выполняются в корневой папке проекта.

Сборка проекта выполняется командой
```
sbt assembly
```
Для сборки Docker-образов запустите скрипт
```
sh scripts/build-images.sh
```
для Linux и Mac, или скрипт
```
scripts/build-images.bat
```
для Windows.
## Запуск
Для имитации Spark-кластера используются Docker-образы, объединённые в файл `docker-compose.yml`.
Для запуска необходимо воспользоваться командой:
```
docker-compose up --scale spark-worker=3
```
После запуска контейнеров для подключения к командной строке внутри одного из них в новом окне введите команду:
```
 docker exec -it most-popular-product_spark-master_1 bash
```
Затем для запуска приложения необходимо выполнить следующую команду:
```
/spark/bin/spark-submit --class application.Main --deploy-mode client --master spark://$(hostname):7077 /opt/spark-apps/most-popular-product-assembly-0.1.0-SNAPSHOT.jar 
```
В результате в примонитрованной к Docker-контейнеру папке [data](https://github.com/mikhaildruzhinin/most-popular-product/tree/main/data) появится папка result, содержащая получившиеся файлы в формате `csv`.