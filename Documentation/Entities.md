### Entities
-----------------------------------------------------------------------------------------------
# User

* Role
  CLIENT,
  MANAGER,
  CONFECTIONER

* State
  NOT_CONFIRMED,
  CONFIRMED,
  DELETED
  BANNED // TODO сделать в будущем если будет время(не разрешать ему оформлять заказы или коменты если они будут)

* Поля 
  id;
  fullName;
  email;
  hashPassword;
  town;
  street;
  houseNumber;
  phoneNumber;
  state;
  Role role;

-----------------------------------------------------------------------------------------------

# Cake

* Category
  CUPCAKES,
  CHEESECAKES,
  MACARONS,
  MOUSSE

* Поля
  id;
  name;
  ingredients;
  price;
  Category category;


-----------------------------------------------------------------------------------------------

# Order

* State
  CREATED,
  IN_PROCESS,
  CANT_FINISH,
  FINISHED
  DELETED

* Поля
  id;
  count;
  description;
  totalPrice;
  creationDate;
  deadline;
  clientId;
  cakeId;
  cakeName;
  confectionerId;
  confectionerIdOtKAZALSJA;
  state;

-----------------------------------------------------------------------------------------------

Добавление продукции менеджером ****
Корзина *****
Отмена заказа пользователем *****