# See http://docs.chef.io/config_rb_knife.html for more information on knife configuration options

current_dir = File.dirname(__FILE__)
log_level                :info
log_location             STDOUT
node_name                "krish123"
client_key               "#{current_dir}/krish123.pem"
validation_client_name   "esamp1-validator"
validation_key           "#{current_dir}/esamp1-validator.pem"
chef_server_url          "https://api.chef.io/organizations/esamp1"
cookbook_path            ["#{current_dir}/../cookbooks"]
