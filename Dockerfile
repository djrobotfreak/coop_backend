FROM djrobotfreak/java8


RUN mkdir -p /var/www/app \
    && mkdir -p /etc/service/coop \
    && mkdir -p /root/staging

ADD . /root/staging

WORKDIR /root/staging

RUN cp build/libs/coop-all.jar /var/www/app \
    && cp docker/service/coop/run /etc/service/coop \
    && cp config.yml /var/www/app \
    && chown -R 777 /var/www/app \
    && apt-get -y autoclean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/ \
    && mkdir -p /var/log/coop/

EXPOSE 8080
CMD [ "/sbin/my_init" ]
