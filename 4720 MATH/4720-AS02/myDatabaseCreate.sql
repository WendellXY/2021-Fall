--
-- juices
--
CREATE TABLE juices (
  id INTEGER PRIMARY KEY,
  juice_name TEXT NOT NULL,
  price REAL NOT NULL
);

--
-- empolyee
--
CREATE TABLE empolyee (
  id INTEGER PRIMARY KEY,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  employee_level REAL NOT NULL
);

--
-- customers
--
CREATE TABLE customers (
  id INTEGER PRIMARY KEY,
  company_name TEXT NOT NULL,
  street_address TEXT NOT NULL,
  city TEXT NOT NULL,
  state TEXT NOT NULL,
  zip TEXT NOT NULL
);

--
-- orders
--
CREATE TABLE orders (
  id INTEGER PRIMARY KEY,
  customer_id INTEGER,
  empolyee_id INTEGER,
  FOREIGN KEY(customer_id) REFERENCES customers(id),
  FOREIGN KEY(empolyee_id) REFERENCES empolyee(id)
);


CREATE TABLE order_items (
  id INTEGER PRIMARY KEY,
  order_id INTEGER,
  product_id INTEGER,
  product_quantity INTEGER,
  FOREIGN KEY(order_id) REFERENCES orders(id),
  FOREIGN KEY(product_id) REFERENCES products(id)
);