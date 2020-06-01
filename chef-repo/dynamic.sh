#!/bin/bash
echo "Creating recipe"
echo ''>new1.rb
cp template.rb new1.rb
#grep -rl default new1.rb | xargs sed -i "s/default/$1/g"

#echo "Creating cookbook"

#cd /home/chef-repo/

#knife cookbook create $1

#cd cookbooks/$1/recipes

#cat /home/ubuntu/chef-repo/default.rb>>default.rb

#echo "Uploading cookbook"

#knife cookbook upload $1

#echo "Success!"

#knife ssh $1 'chef-client' --manual-list --ssh-user root --ssh-password $2
