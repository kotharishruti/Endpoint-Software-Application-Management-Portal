package 'abcd' do
  action :install
end

service 'abcd' do
  action [ :enable, :start ]
end
