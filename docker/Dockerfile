FROM ubuntu:16.04

RUN apt-get update \
 && apt-get install -y libjpeg-dev libtiff-dev libpng-dev \
 && apt-get install -y libxml2 libxml2-dev libgeos-dev libproj-dev \
 && apt-get -y install software-properties-common \
 && apt-get install -y wget \
 && apt-get install -y locales \
     language-pack-ko && \
     dpkg-reconfigure locales && \
     locale-gen ko_KR.UTF-8 && \
     /usr/sbin/update-locale LANG=ko_KR.UTF-8
 # 한글을 출력하기 위해 환경변수 등록
 ENV LANG=ko_KR.UTF-8
 ENV LANGUAGE=ko_KR.UTF-8
 ENV LC_ALL=ko_KR.UTF-8

 RUN echo "/usr/local/lib" >> /etc/ld.so.conf \
  && ldconfig

 RUN add-apt-repository ppa:openjdk-r/ppa \
  && apt-get update --fix-missing \
  && apt-get -y install openjdk-11-jre

 RUN apt-get remove -y software-properties-common
 RUN apt-get remove -y wget

 RUN mkdir /server
 COPY ./server.jar /server

 RUN cd
 CMD ["java", "-jar", "/server/server.jar"]