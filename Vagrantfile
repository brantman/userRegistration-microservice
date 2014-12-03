# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

   config.vm.box = "precise64"
   config.vm.box_url = "http://files.vagrantup.com/precise64.box"
  
   config.vm.define "db" do |db|
      db.vm.provision :shell, path: "./provision_shell/mongo_provision.sh"
      db.vm.provision :shell, path: "./provision_shell/rabbitmq_provision.sh"
      db.vm.network "private_network", ip: "192.168.33.11"
      db.vm.synced_folder ".", "/vagrant", disabled: true
    end

end
