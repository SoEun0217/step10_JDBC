
create table bank(
   account varchar2(10) primary key, --계좌번호
   balance number --잔액
);--alt+x

 insert into bank values('A01',1000);
 insert into bank values('A02',500);
 
 select * from bank;
 
 commit
 select*From copy_emp;
 
 delete from copy_emp where empno=7900



--계좌이체.
--두개는 같은 Connection을 사용해야한다.
--입금금액이 최대금액을 초과하면 두개의 기능이 같이 움직인다 -transactions
1. 인출한다.
update bank set balance=balane-인출금액 where account = 출금계좌번호;

2. 인출한 금액만큼 입금한다.
update bank set balance=balance+인출금액 where account =입금계좌번호;

select balance from bank where account='A02';









