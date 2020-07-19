- python conda env list 查看当前所有的环境,当前所在的环境会有一个*

- 创建一个名为python34的环境，指定Python版本是3.4（不用管是3.4.x，conda会为我们自动寻找3.4.x中的最新版本） 
  conda create --name python34 python=3.4 

- 退出当前环境 （conda deactivate）
- 删除已有环境 (conda remove --name python34 --all)
- 进入某一个环境 （activate python34）
- 用户安装的不同python环境都会被放在目录~/anaconda/envs下
- 查看当前环境下的包（conda list）
- 查看某个指定环境的已安装包（conda list -n python34）
- 查找package信息（conda search numpy）
- 安装package（conda install -n python34 numpy）。如果不用-n指定环境名称，则被安装在当前活跃环境 。也可以通过-c指定通过某个channel安装。
- 更新package（ conda update -n python34 numpy）
- 删除package（conda remove -n python34 numpy）
- 添加Anaconda的TUNA镜像（conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/）