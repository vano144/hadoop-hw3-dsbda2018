# Sum of twitter's message by country in minute.


## Minimal system requirements
* Java 8
* Apache Maven 4.0.0
* docker 18.09.0
* docker-compose 1.23.1

## How to start

* Create jar

  ```bash build.sh```

* Initialize env variables

Before run, please fill your keys.

  ```bash init_env.sh```


* Start ElasticSearch-Logstash-Grafana

  ```docker-compose up -d```
  
* Run it

  ```bash run.sh```
  

# Grafana volume

In folder grafana-data is located volume with configured dashboard and connection to elasticsearch.
   

