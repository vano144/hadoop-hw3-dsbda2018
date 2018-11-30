# Sum of twitter's message by country in minute.


## Minimal system requirements
* Java 8
* Apache Maven 3.5
* docker 18.09.0
* docker-compose 1.23.1
* Vagrant 2.2.2

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
   

# Run it in vagrant

```vagrant plugin install vagrant-docker-compose```
```vagrant up```

You may see result on `127.0.0.1:8082`