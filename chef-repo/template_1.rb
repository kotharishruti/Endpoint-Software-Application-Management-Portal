yum_package 'default_path' do
  action :install
  source "path"
end

#service 'default_path' do
  #action [ :enable, :start ]
#end
