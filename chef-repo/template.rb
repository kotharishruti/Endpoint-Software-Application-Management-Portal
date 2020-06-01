package 'default' do
  action :install
end

service 'default' do
  action [ :enable, :start ]
end
