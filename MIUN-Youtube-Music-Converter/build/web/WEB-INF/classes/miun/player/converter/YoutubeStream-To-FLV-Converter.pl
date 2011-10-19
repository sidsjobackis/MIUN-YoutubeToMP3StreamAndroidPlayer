#####################################
# Youtube stream to *.mp4 converter #
# /Jonas Bäckström  				#
#####################################


use LWP;
use URI::Escape;
$u=\&uri_unescape; #Return 
for(@ARGV){
	$tmp = $_;
	( $_, $i ) = shift =~ /^(.+m)\/.+v=(.+)/;
	$_ = $tmp;
	($y,$_)=m[(.+m)/.+[=/]([\w-]{11})] or next;
	&$u(
		($l=new LWP::UserAgent)
			->get("$y/get_video_info?&video_id=$_")
				->content
	)=~/ap=url=([^&]+).+&title=([^&]+)/;
	$l->mirror( &$u($1),
		do{
			$t=&$u($2);
			$t=~y#+/# #;
			"$i.mp4"
		}
	);
}


# use LWP;
# use URI::Escape;
# $u=\&uri_unescape; #Return 
# for(@ARGV){
	# ($y,$_)=m[(.+m)/.+[=/]([\w-]{11})] or next;
	# &$u(
		# ($l=new LWP::UserAgent)
			# ->get("$y/get_video_info?&video_id=$_")
				# ->content
	# )=~/ap=url=([^&]+).+&title=([^&]+)/;
	# $l->mirror( &$u($1),
		# do{
			# $t=&$u($2);
			# $t=~y#+/# #;
			# "$t.mp4"
		# }
	# )
# }
