package course.labs.todomanager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";
    ViewHolder mHolder;

	public ToDoListAdapter(Context context) {

		mContext = context;

	}


	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// TODO - Get the current ToDoItem
		final ToDoItem toDoItem = (ToDoItem)this.getItem(position);

		// TODO - Inflate the View for this ToDoItem
		// from todo_item.xml
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RelativeLayout itemLayout = (RelativeLayout)convertView;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.todo_item, null, false);
            itemLayout = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout1);

             ViewHolder viewHolder = new ViewHolder();
             viewHolder.titleView = (TextView)convertView.findViewById(R.id.titleView);
             viewHolder.dateView = (TextView) convertView.findViewById(R.id.dateView);
             viewHolder.priorityView = (TextView) convertView.findViewById(R.id.priorityView);
             viewHolder.statusView = (CheckBox) convertView.findViewById(R.id.statusCheckBox);
             itemLayout.setTag(viewHolder);
        }

        // TODO - Fill in specific ToDoItem data
        // Remember that the data that goes in this View
        // corresponds to the user interface elements defined
        // in the layout file

        final ViewHolder vHolder =(ViewHolder)itemLayout.getTag();

        vHolder.titleView.setText(toDoItem.getTitle());

        if (toDoItem.getStatus()== ToDoItem.Status.DONE){
            vHolder.statusView.setChecked(true);
        }  else {
            vHolder.statusView.setChecked(false);
        }

        vHolder.statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Log.i(TAG, "Entered onCheckedChanged()");
                // TODO - set up an OnCheckedChangeListener, which
                if(vHolder.statusView.isChecked()== true){
                    toDoItem.setStatus(ToDoItem.Status.DONE);
                    vHolder.statusView.setChecked(true);
                } else {
                    toDoItem.setStatus(ToDoItem.Status.NOTDONE);
                    vHolder.statusView.setChecked(false);
                }


            }
        });

        vHolder.dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));
        vHolder.priorityView.setText(toDoItem.getPriority().toString());



		// TODO - Display Title in TextView
		//final TextView titleView = null;

		// TODO - Set up Status CheckBox
		//final CheckBox statusView = null;



		// TODO - Display Priority in a TextView

		//final TextView priorityView = null;

		// TODO - Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
		// time String

		//final TextView dateView = null;

		// Return the View you just created
		return itemLayout;

	}
    class ViewHolder{
        TextView titleView;
        TextView priorityView;
        TextView dateView;
        CheckBox statusView;
    }
}
