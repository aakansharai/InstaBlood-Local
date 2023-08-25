package com.example.blooddonation.UserData

object Methods {
    val listData = mutableListOf<ResisteredDataList>()

    fun setData(name : String, phone: String, aadhar: String){
        listData.add(ResisteredDataList(name, phone, aadhar))
    }
    fun getData() : List<ResisteredDataList>
    {
        return listData
    }
}