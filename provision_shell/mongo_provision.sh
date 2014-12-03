#!/usr/bin/env bash
echo "Installing MongoDB and setting it up..."
apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10
echo 'deb http://downloads-distro.mongodb.org/repo/ubuntu-upstart dist 10gen' | tee /etc/apt/sources.list.d/mongodb.list
apt-get -y update >/dev/null 2>&1
apt-get -y install mongodb-org >/dev/null 2>&1

echo "Setting up mongod.conf to listen on all interfaces..."
sed -i 's/bind_ip/#bind_ip/' /etc/mongod.conf
echo "Adding execute permission for mongod.conf..."
chmod +x /etc/mongod.conf
echo "Restartting mongo db..."
service mongod restart
