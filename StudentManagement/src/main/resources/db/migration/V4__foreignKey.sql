ALTER TABLE PUBLIC.TB_DETAIL_CARD
ADD FOREIGN KEY (student_id) 
REFERENCES PUBLIC.TB_STUDENT(identification);