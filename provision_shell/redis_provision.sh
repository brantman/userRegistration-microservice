echo "Installing Redis and setting it up..."
apt-get update >/dev/null 2>&1
apt-get install -y redis-server >/dev/null 2>&1

echo "Setting up redis.conf to listen on all interfaces..."
sed -i 's/bind\s\+127.0.0.1/#bind 127.0.0.1/' /etc/redis/redis.conf
echo "Adding execute permission for redis.conf..."
chmod +x /etc/redis/redis.conf
echo "Restartting redis..."
service redis-server restart