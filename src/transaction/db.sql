
create table bank(
   account varchar2(10) primary key, --���¹�ȣ
   balance number --�ܾ�
);--alt+x

 insert into bank values('A01',1000);
 insert into bank values('A02',500);
 
 select * from bank;
 
 commit
 select*From copy_emp;
 
 delete from copy_emp where empno=7900



--������ü.
--�ΰ��� ���� Connection�� ����ؾ��Ѵ�.
--�Աݱݾ��� �ִ�ݾ��� �ʰ��ϸ� �ΰ��� ����� ���� �����δ� -transactions
1. �����Ѵ�.
update bank set balance=balane-����ݾ� where account = ��ݰ��¹�ȣ;

2. ������ �ݾ׸�ŭ �Ա��Ѵ�.
update bank set balance=balance+����ݾ� where account =�Աݰ��¹�ȣ;

select balance from bank where account='A02';









