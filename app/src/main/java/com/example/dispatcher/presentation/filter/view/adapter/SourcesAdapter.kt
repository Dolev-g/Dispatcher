import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispatcher.databinding.AdapterSourceItemBinding
import com.example.dispatcher.presentation.filter.view.model.EnumFilter
import com.example.dispatcher.presentation.filter.viewModel.FilterViewModel

class SourcesAdapter(
    private val sources: List<String>,
    private val filterViewModel: FilterViewModel,
    private val filterType : EnumFilter

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

            binding.Wrapper.setOnClickListener {
                if (filterType== EnumFilter.SOURCES){
                    filterViewModel.setSourcesChoosen(sourceName)
                }
                else{
                    filterViewModel.setSearchInChoosen(sourceName)
                }
                filterViewModel.setFilterChoose(false)
            }
        }
    }
}
