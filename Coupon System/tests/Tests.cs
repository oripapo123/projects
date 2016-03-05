using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data.Linq;
using System.Threading.Tasks;
using NUnit.Framework;
using DataBase;
using ServerBusinessLogic;
using ServerServiceLayer;
namespace tests
{
    [TestFixture]
    class Tests
    {

        Client ori = new Client();

        [Test]
        public void addManager(){
            MyDataBaseDataContext db = new MyDataBaseDataContext();
            System_Manager manager = new System_Manager();
            manager.birth_date = new DateTime(1985, 6, 6);
            manager.first_name = "david";
            manager.last_name = "ben david";
            manager.password = "qwerty";
            manager.email = "dvd@gmail.com";
            manager.phone = "0501239874";
            manager.username = "bendavid";

            int actual = db.System_Managers.Count();
            int expected = actual + 1;
            db.System_Managers.InsertOnSubmit(manager);

            db.SubmitChanges();
            actual = db.System_Managers.Count();
            Assert.AreEqual(expected,actual);

        }

        [Test]
        public void bEditManager()
        {

            MyDataBaseDataContext db = new MyDataBaseDataContext();
            var result = (from a in db.System_Managers
                          where a.username == "bendavid"
                          select a).First();
            String expected = "test@test";
            ((System_Manager)result).email = "test@test";
            db.SubmitChanges();
            result = (from a in db.System_Managers
                      where a.username == "bendavid"
                      select a).First();
            String actual = ((System_Manager)result).email;
            actual.Trim();
            Assert.AreEqual(actual, expected);
        }

        [Test]
        public void deleteManager()
        {
            {
                MyDataBaseDataContext db = new MyDataBaseDataContext();
                var result = (from a in db.System_Managers
                              where a.username == "bendavid"
                              select a).First();
                int actual = db.System_Managers.Count();
                int expected = actual - 1;
                db.System_Managers.DeleteOnSubmit(result);
                db.SubmitChanges();
                actual = db.System_Managers.Count();
                Assert.AreEqual(expected, actual);

            }

        }
        [Test]
        public void checkName()
        {
            MyDataBaseDataContext db = new MyDataBaseDataContext();
            var result = (from a in db.Clients
                          where a.username == "oripapo2"
                          select a).First();
            String actual = result.first_name.ToString();
            actual = actual.Substring(0, 4);
            Assert.AreEqual("orib", actual);
        }

        

        [Test]
        public void addCoupon()
        {
            MyDataBaseDataContext db = new MyDataBaseDataContext();
            Coupon VacuumCoupon = new Coupon();
            VacuumCoupon.categoryID = 22;
            VacuumCoupon.couponCategory = 2;
            VacuumCoupon.description = "the best vacuum in the world!";
            VacuumCoupon.discountedPrice = 80;
            VacuumCoupon.isApproved = true;
            VacuumCoupon.lastDate = new DateTime(2015, 11, 5);
            VacuumCoupon.name = "vacuum";
            VacuumCoupon.orginialPrice = 100;
            VacuumCoupon.rating = 5;
            VacuumCoupon.Serial_Number = 321;

            
            int actual = db.Coupons.Count();
            int expected = actual + 1;
            db.Coupons.InsertOnSubmit(VacuumCoupon);

            db.SubmitChanges();
            actual = db.Coupons.Count();
            Assert.AreEqual(expected, actual);
           }

        [Test]
        public void deleteCoupon() {
            MyDataBaseDataContext db = new MyDataBaseDataContext();
                var result = (from a in db.Coupons
                              where a.Serial_Number == 321
                              select a).First();
                int actual = db.Coupons.Count();
                int expected = actual - 1;
                db.Coupons.DeleteOnSubmit(result);
                db.SubmitChanges();
                actual = db.Coupons.Count();
                
                Assert.AreEqual(expected, actual);
            }

        [Test]
        public void addClient(){
            MyDataBaseDataContext db = new MyDataBaseDataContext();
            ori.birth_day = new DateTime(1991, 5, 1);
            ori.first_name = "ori";
            ori.last_name = "papo";
            ori.password = "9440990";
            ori.email = "abc@gmail.com";
            ori.phone = "0508626501";
            ori.username = "oriTest";
            
            int actual = db.Clients.Count();
            int expected = actual + 1;
            db.Clients.InsertOnSubmit(ori);

            db.SubmitChanges();
            actual = db.Clients.Count();
            Assert.AreEqual(expected, actual);
            }

        [Test]
        public void getUserType()
        {

            LoggedInUser iu = new LoggedInUser(ori);
            Assert.AreEqual(iu.getUserType(), 0);
        }
        [Test]
        public void getName()
        {

            LoggedInUser iu = new LoggedInUser(ori);
            Assert.AreEqual(iu.getName(), "ori");
        }

        [Test]
        public void getPassword()
        {

            LoggedInUser iu = new LoggedInUser(ori);
            Assert.AreEqual(iu.getPassword(), "9440990");
        }

        [Test]
        public void countCoupons()
        {
            MyDataBaseDataContext db = new MyDataBaseDataContext();
            var result = (from a in db.Coupons
                          select a).ToList();
            int actual = db.Coupons.Count();
            Assert.AreEqual(result.Count, actual);
        }

        [Test]
        public void countBusinessOwners()
        {
            MyDataBaseDataContext db = new MyDataBaseDataContext();
            var result = (from a in db.Business_Owners
                          select a).ToList();
            int actual = db.Business_Owners.Count();
            Assert.AreEqual(result.Count, actual);
        }
        
        [Test]
        public void getUserName()
        {

            LoggedInUser iu = new LoggedInUser(ori);
            Assert.AreEqual(iu.getUserName(), "oriTest");
        }


        [Test]
        public void addBusinessOwner()
        {
            Business_Owner manager = new Business_Owner();
            MyDataBaseDataContext db = new MyDataBaseDataContext();
            manager.birth_date = new DateTime(1991, 5, 1);
            manager.first_name = "ori";
            manager.last_name = "papo";
            manager.password = "9440990";
            manager.email = "abc@gmail.com";
            manager.phone = "0508626501";
            manager.username = "manager";

            int actual = db.Business_Owners.Count();
            int expected = actual + 1;
            db.Business_Owners.InsertOnSubmit(manager);

            db.SubmitChanges();
            actual = db.Clients.Count();
            Assert.AreEqual(expected, actual);
        }

        [Test]
        public void deleteBusinessOwner()
        {

            MyDataBaseDataContext db = new MyDataBaseDataContext();
            var result = (from a in db.Business_Owners
                          where a.username == "manager"
                          select a).First();
            int actual = db.Business_Owners.Count();
            int expected = actual - 1;
            db.Business_Owners.DeleteOnSubmit(result);
            db.SubmitChanges();
            actual = db.Business_Owners.Count();

            Assert.AreEqual(expected, actual);

        }

        [Test]
        public void bEditClient()
        {

            MyDataBaseDataContext db = new MyDataBaseDataContext();
            var result = (from a in db.Clients
                          where a.username == "oriTest"
                          select a).First();
            String expected = "test@test";
            ((Client)result).email = "test@test";
            db.SubmitChanges();
            result = (from a in db.Clients
                          where a.username == "oriTest"
                          select a).First();
            String actual = ((Client)result).email;
            actual.Trim();
            Assert.AreEqual(actual, expected);
        }

        [Test]
        public void deleteClient() {

            MyDataBaseDataContext db = new MyDataBaseDataContext();
            var result = (from a in db.Clients
                          where a.username == "oriTest"
                          select a).First();
            int actual = db.Clients.Count();
            int expected = actual - 1;
            db.Clients.DeleteOnSubmit(result);
            db.SubmitChanges();
            actual = db.Clients.Count();

            Assert.AreEqual(expected, actual);
            
        }

        [Test]
        public void addBusiness()
        {
            MyDataBaseDataContext db = new MyDataBaseDataContext();
            Business business = new Business();
            business.address = "Rager 12";
            business.business_name = "sweetshop";
            business.category = 2;
            business.city = "beer sheva";


            int actual = db.Businesses.Count();
            int expected = actual + 1;
            db.Businesses.InsertOnSubmit(business);

            db.SubmitChanges();
            actual = db.Businesses.Count();
            Assert.AreEqual(expected, actual);
 
        }

        [Test]
        public void bEditBusiness()
        {

            MyDataBaseDataContext db = new MyDataBaseDataContext();
            var result = (from a in db.Businesses
                          where a.business_name == "sweetshop"
                          select a).First();
            String expected = "haifa";
            ((Business)result).city = "haifa";
            db.SubmitChanges();
            result = (from a in db.Businesses
                      where a.business_name == "sweetshop"
                      select a).First();
            String actual = ((Business)result).city;
            actual.Trim();
            Assert.AreEqual(actual, expected);
        }

        [Test]
        public void deleteBusiness()
        {

            MyDataBaseDataContext db = new MyDataBaseDataContext();
            var result = (from a in db.Businesses
                          where a.business_name == "sweetshop"
                          select a).First();
            int actual = db.Businesses.Count();
            int expected = actual - 1;
            db.Businesses.DeleteOnSubmit(result);
            db.SubmitChanges();
            actual = db.Businesses.Count();

            Assert.AreEqual(expected, actual);
            
        }
        
     }




    }
        


