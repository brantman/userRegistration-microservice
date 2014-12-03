#!/usr/bin/env bash
echo "Installing Java8 and setting it up..."
add-apt-repository ppa:webupd8team/java >/dev/null 2>&1
apt-get update >/dev/null 2>&1
apt-get install -y oracle-java8-installer >/dev/null 2>&1
apt-get install -y oracle-java8-set-default