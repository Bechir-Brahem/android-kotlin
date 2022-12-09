import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.R
import com.example.tp2.Student
import java.util.*
import kotlin.collections.ArrayList

class StudentListAdapter(var students: ArrayList<Student>) :
    RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {
    var dataFilterList = ArrayList<Student>()

    init {
        dataFilterList = students
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val checkBoxPresent = itemView.findViewById<CheckBox>(R.id.checkBoxPresent)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentListAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.row_student, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: StudentListAdapter.ViewHolder, position: Int) {
        val student: Student = dataFilterList.get(position)
        val textView = viewHolder.textView
        val imageView = viewHolder.imageView
        val checkBoxPresent = viewHolder.checkBoxPresent


        checkBoxPresent.setOnClickListener() {
            student.present = checkBoxPresent.isChecked
        }
        val nom = student.nom
        val prenom = student.prenom
        textView.setText("$nom  $prenom")
        if (student.genre == true) {
            imageView.setImageResource(R.drawable.man)
        } else {
            imageView.setImageResource(R.drawable.woman)
        }
        checkBoxPresent.isChecked = student.present
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    fun filterAbsent() {
        val resultList = ArrayList<Student>()
        for (student in students) {
            if (!student.present)
                resultList.add(student)
        }
        dataFilterList=resultList
        notifyDataSetChanged();
    }
    fun filterPresent() {
        val resultList = ArrayList<Student>()
        for (student in students) {
            if (student.present)
                resultList.add(student)
        }
        dataFilterList=resultList
        notifyDataSetChanged();
    }
    fun noFilter(){
        dataFilterList=students
        notifyDataSetChanged();
    }


}