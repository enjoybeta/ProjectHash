package hash.application.helpers

import java.io.File
import java.io.FileReader
import com.google.gson.Gson
import hash.application.dataType.*

/**
 * Created by gouji on 2/20/2018.
 */
class FileManager(_dir:File,_fileName: String) {
    private val dir:File = _dir
    private val fileName:String = _fileName

    fun checkFile():Boolean{
        return File(dir,fileName).exists()
    }

    fun proofFile(){
        if(!checkFile()){
            val file = File(dir,fileName)
            file.writeText("{}")
        }
    }

    fun readFile(): String {
        val file = File(dir,fileName)
        return file.readText()
    }

//TODO
//    fun initFile(){
//        val file = File(dir,fileName)
//        file.writeText("{}")
//    }

}