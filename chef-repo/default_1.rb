yum_package 'default_http://www.rabbitmq.com/releases/rabbitmq-server/v3.0.1/rabbitmq-server-3.0.1-1.noarch.rpm' do
  action :install
  source "http://www.rabbitmq.com/releases/rabbitmq-server/v3.0.1/rabbitmq-server-3.0.1-1.noarch.rpm"
end

#service 'default_http://www.rabbitmq.com/releases/rabbitmq-server/v3.0.1/rabbitmq-server-3.0.1-1.noarch.rpm' do
  #action [ :enable, :start ]
#end
