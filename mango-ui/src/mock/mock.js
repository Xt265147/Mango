import Mock from 'mockjs'

Mock.mock('http://localhost:8001/login', {
  'token': '123456'
})

Mock.mock('http://localhost:8001/user', {
  'name': '@name',
  'email': '@email',
  'age|1-10': 5
})
Mock.mock('http://localhost:8001/menu', {
  'id': '@increment',
  'name': 'menu',
  'order|1-20': 5
})
