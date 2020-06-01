#!/bin/bash

echo $1 #path

echo "Creating recipe"

echo ''>default_url.rb
echo ''>/home/esamp/chef-repo/cookbooks/default_url/recipes/default.rb

cat template_url.rb>default_url.rb

grep -rl url default_url.rb | xargs sed -i "s#url#$1#g"

echo "Creating cookbook"

#cd /home/esamp/chef-repo/

knife cookbook create default_url

cd /home/esamp/chef-repo/cookbooks/default_url/recipes

cat /home/esamp/chef-repo/default_url.rb>>default.rb

echo "Uploading cookbook"

knife cookbook upload default_url

echo "Success!"

knife node run_list add N1.1.5.22 'recipe[default_url]'

echo "added to runlist"

knife ssh 1.1.5.22 'chef-client' --manual-list --ssh-user root --ssh-password fedora

echo "Running chef-client!"

knife node run_list remove N1.1.5.22 'recipe[default_url]'
