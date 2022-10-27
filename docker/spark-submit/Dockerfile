FROM spark-base:latest

RUN apt-get update && apt-get install -y dos2unix
COPY spark-submit.sh /
RUN dos2unix /spark-submit.sh && apt-get --purge remove -y dos2unix && rm -rf /var/lib/apt/lists/*

ENV SPARK_MASTER_URL="spark://spark-master:7077"
ENV SPARK_SUBMIT_ARGS=""
ENV SPARK_APPLICATION_ARGS ""

CMD ["/bin/bash", "/spark-submit.sh"]
