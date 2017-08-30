FROM openjdk:8-jdk

# Install maven
RUN mkdir /mvn \
    && cd /mvn \
    && wget http://www-us.apache.org/dist/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.zip \
    && unzip apache-maven-3.5.0-bin.zip \
    && echo 'export PATH=/mvn/apache-maven-3.5.0/bin:$PATH' >> ~/.bashrc \
    && echo "alias mci='mvn clean install'" >> ~/.bashrc \
    && echo "alias jcj='java -XX:+UnlockDiagnosticVMOptions -XX:+WhiteBoxAPI -XX:-RestrictContended -jar'" >> ~/.bashrc

VOLUME /jcstress
WORKDIR /jcstress
ENTRYPOINT tail -f /dev/null
