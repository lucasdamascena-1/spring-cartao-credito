CREATE TABLE tb_detail_card (
  operation_id  integer NOT NULL,
  student_id varchar(200) NOT NULL,
  description_operation varchar(200) NOT NULL,
  date_operation date DEFAULT NULL,
  type_operation char(1) DEFAULT NULL,
  value_operation decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (operation_id,student_id)
);