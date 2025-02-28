package vcnmb.rwanvig.grannyapp2025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
var SignedIN: Int =-1
var arrUser =ArrayList<User>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var Username : EditText = findViewById(R.id.txtName)
        var Password :EditText = findViewById(R.id.txtPassword)

        arrUser.add(User("Granny","1234","https://picsum.photos/200/300"))
        arrUser.add(User("Granpa","4321","https://picsum.photos/200/300"))
        arrUser.add(User("Timmy","6969","https://picsum.photos/200/300"))
        arrUser.add(User("John","6565","https://images.pexels.com/photos/4016579/pexels-photo-4016579.jpeg?auto=compress&cs=tinysrgb&w=600"))

        var btnLogin : Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener()
        {
            var found = false
            SignedIN=-1
            for (i in 0 .. arrUser.size-1)
            {
                if (Username.text.toString().equals(arrUser[i].Name)
                    and Password.text.toString().equals(arrUser[i].Password) )
                {
                    Toast.makeText(this,"Name :"+Username.text+"\nPassword :"+
                    Password.text,Toast.LENGTH_SHORT).show()
                    found=true
                    SignedIN=i
                    val intent = Intent (this,Welcome::class.java)
                    startActivity(intent)
                    break;
                }
            }
            if(found==false)
            {
                Toast.makeText(this,"RUNNNNNNNNNNNNNNN...",Toast.LENGTH_SHORT).show()
            }
        }

    }
}