import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispatcher.databinding.AdapterSourceItemBinding

class SourcesAdapter(
    private val sources: List<String>
) : RecyclerView.Adapter<SourcesAdapter.SourceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val binding = AdapterSourceItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(sources[position])
    }

    override fun getItemCount(): Int = sources.size

    inner class SourceViewHolder(private val binding: AdapterSourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sourceName: String) {
            binding.sourceText.text = sourceName
        }
    }
}
