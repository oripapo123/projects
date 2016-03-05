using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ServerBL
{
    public class UserFuncs : IFunc
    {
        DataBase.MyDataBaseDataContext db = new DataBase.MyDataBaseDataContext();
        void IFunc.addCoupon(DataBase.Coupon coupon)
        {
            
        }

        void IFunc.approveCoupon(DataBase.Coupon coupon)
        {
            
        }

        void IFunc.editCoupon(DataBase.Coupon coupon)
        {
            throw new NotImplementedException();
        }

        void IFunc.addBusiness(string name, string address, string city, string description, int category)
        {
            throw new NotImplementedException();
        }

        void IFunc.editBusiness(DataBase.Business business)
        {
            throw new NotImplementedException();
        }

        void IFunc.deleteBusiness(DataBase.Business business)
        {
            throw new NotImplementedException();
        }

        void IFunc.adduser()
        {
            throw new NotImplementedException();
        }

        void IFunc.loginUser(string username, string password)
        {
            throw new NotImplementedException();
        }

        void IFunc.sendPasswordByName(string username)
        {
            throw new NotImplementedException();
        }

        void IFunc.buyCoupon(DataBase.Coupon coupon)
        {
            throw new NotImplementedException();
        }

        void IFunc.rateCoupon(DataBase.Coupon coupon)
        {
            throw new NotImplementedException();
        }

        void IFunc.useCoupon(DataBase.Coupon coupon)
        {
            throw new NotImplementedException();
        }

        void IFunc.notifyAboutCoupon(DataBase.Coupon coupon)
        {
            throw new NotImplementedException();
        }
    }
}
