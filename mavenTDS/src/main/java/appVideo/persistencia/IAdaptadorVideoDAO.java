package appVideo.persistencia;

import java.util.List;
import appVideo.dominio.Video;

public interface IAdaptadorVideoDAO {
	public void createVideo(Video video);
	public void deleteVideo(Video video);
	public void updateVideo(Video video);
	public Video getVideo(int codigo);
	public List<Video> getAll();
}
